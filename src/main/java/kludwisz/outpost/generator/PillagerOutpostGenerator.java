package kludwisz.outpost.generator;

import java.util.*;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.util.block.BlockBox;
import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.block.BlockMirror;
import com.seedfinding.mccore.util.block.BlockRotation;
import com.seedfinding.mccore.util.data.Pair;
import com.seedfinding.mccore.util.pos.BPos;
import com.seedfinding.mccore.util.pos.CPos;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcseed.rand.JRand;

import kludwisz.outpost.jigsaws.JigsawBlock;
import kludwisz.outpost.jigsaws.PillagerOutpostJigsawBlocks;
import kludwisz.outpost.jigsaws.PillagerOutpostPoolYMax;
import kludwisz.outpost.jigsaws.PillagerOutpostPools;
import kludwisz.outpost.pieces.PillagerOutpostPieceNames;
import kludwisz.outpost.pieces.PillagerOutpostStructureSize;
import kludwisz.util.SequencedPriorityIterator;
import kludwisz.util.VoxelShape;


public class PillagerOutpostGenerator {
    private static final int MAX_DIST = 80; // max distance from start piece
    private static final int MAX_DEPTH = 7; // defined as "size" in the client jar
    private static final int EMPTY_PIECE_ID = 11;
    private static final int EMPTY_POOL_ID = 4;
    private static final int START_POOL_ID = 0; // outpost/base_plates

    private static final ArrayList<Integer> START_TEMPLATES = getTemplatesFromPool(
            Objects.requireNonNull(PillagerOutpostPools.get(START_POOL_ID)));

    private List<Piece> pieces;

    public PillagerOutpostGenerator() {}

    public boolean generate(long worldseed, int chunkX, int chunkZ, ChunkRand rand) {
        this.pieces = new ArrayList<>();

        // choose random starting template and rotation
        rand.setCarverSeed(worldseed, chunkX, chunkZ, MCVersion.v1_21);
        int pickedY = 0; // uses terrain height, we're skipping it for simplicity
        BlockRotation rotation = BlockRotation.getRandom(rand);

        int template = rand.getRandom(START_TEMPLATES);
        BPos size = PillagerOutpostStructureSize.get(template);

        // set starting position
        BPos startPos = new CPos(chunkX, chunkZ).toBlockPos(pickedY);

        BlockBox box = BlockBox.getBoundingBox(startPos, rotation, BPos.ORIGIN, BlockMirror.NONE, size);
        int centerX = (box.minX + box.maxX) / 2;
        int centerZ = (box.minZ + box.maxZ) / 2;
        int centerY = box.minY + 1;
        int y = startPos.getY() + 1;

        // create the first piece
        Piece piece = new Piece(template, startPos, box, rotation, 0);

        // create structure max bounding box
        BlockBox fullBox = new BlockBox(centerX - MAX_DIST, y - MAX_DIST, centerZ - MAX_DIST, centerX + MAX_DIST + 1, y + MAX_DIST + 1, centerZ + MAX_DIST + 1);
        Assembler assembler = new Assembler(MAX_DEPTH, this.pieces, y);
        assembler.pieces.add(piece);
        VoxelShape a = new VoxelShape(fullBox);
        a.fullBoxes.add(new BlockBox(box.minX,box.minY,box.minZ,box.maxX+1,box.maxY+1,box.maxZ+1));
        piece.voxelShape = a;

        // place pieces
        assembler.tryPlacing(piece, rand);
        while(assembler.placing.hasNext()) {
            Piece nextPiece = assembler.placing.next();
            if (nextPiece == null)
                break; // end of input

            assembler.tryPlacing(nextPiece, rand);
        }

        return true;
    }

    public List<Piece> getPieces() {
        return this.pieces;
    }

    public void printPieces() {
        for (Piece p : this.pieces) {
            System.out.print(p.getName() + "  /tp  " + p.box.minX + " " + p.box.minY + " " + p.box.minZ);
            System.out.println("  /tp  " + p.box.maxX + " " + p.box.maxY + " " + p.box.maxZ);
        }
    }

    static public class Piece {
        int id;
        public BPos pos;
        BlockBox box;
        public BlockRotation rotation;
        private VoxelShape voxelShape;
        int depth;

        public String getName(){
            return PillagerOutpostPieceNames.get(this.id);
        }

        Piece(int id, BPos pos, BlockBox box, BlockRotation rotation, int depth) {
            this.id = id;
            this.pos = pos;
            this.box = box;
            this.rotation = rotation;
            this.voxelShape = new VoxelShape(box);
            this.depth = depth;
        }

        public void move(int x, int y, int z) {
            box.move(x, y, z);
            pos = pos.add(x, y, z);
        }

        public List<BlockJigsawInfo> getShuffledJigsawBlocks(BPos offset, JRand rand) {//taking 20% need to opti
            List<JigsawBlock> blocks = PillagerOutpostJigsawBlocks.get(this.id);
            List<BlockJigsawInfo> list = new ArrayList<>(blocks.size());

            for (JigsawBlock b : blocks) {
                BlockJigsawInfo blockJigsawInfo = new BlockJigsawInfo(b, rotation.rotate(b.relativePos, new BPos(0,0,0)).add(offset), rotation );
                list.add(blockJigsawInfo);
            }
            rand.shuffle(list);

            list.sort(
                    Comparator.comparingInt(
                            (var0x) -> {
                                return -var0x.nbt.getSelectionPriority();
                            }
                    )
            );

            return list;
        }

        public void setVoxelShape(VoxelShape mutableobject1) {
            this.voxelShape = mutableobject1;
        }

        public VoxelShape getVoxelShape() {
            return this.voxelShape;
        }
    }

    public static class BlockJigsawInfo {
        JigsawBlock nbt;
        BPos pos;
        BlockRotation rotation;
        public BlockJigsawInfo(JigsawBlock nbt, BPos pos, BlockRotation rotation) {
            this.nbt = nbt;
            this.pos = pos;
            this.rotation = rotation;
        }

        public BlockDirection getFront() {
            return rotation.rotate(this.nbt.direction1);
        }

        public BlockDirection getTop() {
            return rotation.rotate(this.nbt.direction2);
        }

        public BlockDirection getOpposite(BlockDirection b){
            switch (b) {
                case NORTH:
                    return BlockDirection.SOUTH;
                case SOUTH:
                    return BlockDirection.NORTH;
                case WEST:
                    return BlockDirection.EAST;
                case EAST:
                    return BlockDirection.WEST;
                case DOWN:
                    return BlockDirection.UP;
                case UP:
                    return BlockDirection.DOWN;
                default:
                    throw new IllegalStateException("Unable to get facing of " );
            }
        }

        public boolean canAttach(BlockJigsawInfo blockJigsawInfo2, BlockDirection direction) {
            return direction == this.getOpposite(blockJigsawInfo2.getFront())
                    && this.nbt.targetName.equals(blockJigsawInfo2.nbt.name)
                    && (this.nbt.jointType.isRollable() || this.getTop().equals(blockJigsawInfo2.getTop()));
        }

        public int getPoolType() {
            return this.nbt.poolType;
        }
    }

    public static class Assembler {
        int maxDepth;
        List<Piece> pieces;

        private final SequencedPriorityIterator<Piece> placing = new SequencedPriorityIterator<>();

        Assembler(int maxDepth, List<Piece> pieces, int heightY) {
            this.maxDepth = maxDepth;
            this.pieces = pieces;
        }

        public void tryPlacing(Piece piece, ChunkRand rand) {
            int depth = piece.depth;
            BPos pos = piece.pos;
            VoxelShape mutableobject = new VoxelShape();
            BlockBox box = piece.box;
            int minY = box.minY;

            // System.out.println("Shuffle for piece " + piece.getName());
            label139:
            for (BlockJigsawInfo blockJigsawInfo : piece.getShuffledJigsawBlocks(pos, rand)) {
                // System.out.println(blockJigsawInfo.nbt.name);
                BlockDirection blockDirection = blockJigsawInfo.getFront();
                BPos blockPos = blockJigsawInfo.pos;
                BPos relativeBlockPos = new BPos(blockPos.getX() + blockDirection.getVector().getX(),
                        blockPos.getY() + blockDirection.getVector().getY(),
                        blockPos.getZ() + blockDirection.getVector().getZ());
                int y = blockPos.getY() - minY;

                List<Pair<Integer, Integer>> pool = PillagerOutpostPools.get(blockJigsawInfo.getPoolType());

                if (pool != null && !pool.isEmpty()) {
                    int fallbackPoolID = PillagerOutpostPools.getFallbackID(blockJigsawInfo.getPoolType());
                    List<Pair<Integer, Integer>> fallbackPool = PillagerOutpostPools.get(fallbackPoolID);

                    if (fallbackPool != null && !fallbackPool.isEmpty()) {
                        // System.out.println("got non-empty pools");

                        boolean isInside = box.contains(relativeBlockPos);
                        VoxelShape mutableobject1;
                        if (isInside) {
                            mutableobject1 = mutableobject;
                            if (mutableobject.isNull()) {
                                mutableobject.setValue(box,true);
                            }
                        } else {
                            mutableobject1 = piece.getVoxelShape();
                        }

                        // create a list of shuffled templates
                        ArrayList<Integer> list = new ArrayList<>();

                        if (depth != this.maxDepth) {
                            list = getShuffledTemplatesFromPool(pool, rand);
                        }
                        list.addAll(getShuffledTemplatesFromPool(fallbackPool, rand));

                        // System.out.println(list.size());

                        // aka var30 in the code
                        int placementPriority = blockJigsawInfo.nbt.getPlacementPriority();

                        for (int jigsawpiece1 : list) {
                            if (jigsawpiece1 == EMPTY_PIECE_ID){
                                // System.out.println("break: empty");
                                break;
                            }

                            List<BlockRotation> shuffledRotations = BlockRotation.getShuffled(rand);
                            for (BlockRotation rotation1 : shuffledRotations) {

                                BPos size1 = PillagerOutpostStructureSize.get(jigsawpiece1);
                                BlockBox box1 = size1==null ? new BlockBox(0,0,0,0,0,0) : BlockBox.getBoundingBox(BPos.ORIGIN, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                                Piece piece1 = new Piece(jigsawpiece1, BPos.ORIGIN, box1, rotation1, 0);
                                List<BlockJigsawInfo> list1;

                                list1 = piece1.getShuffledJigsawBlocks(BPos.ORIGIN, rand);

                                // Outpost uses expansion hack
                                int l = 0;
                                if (box1.getYSpan() <= 16) {
                                    l = maxHeightOfList(list1, box1);
                                }

                                BlockDirection direction = blockJigsawInfo.getFront();

                                // Loop to see if we can attach
                                for (BlockJigsawInfo blockJigsawInfo2 : list1) {
                                    if (blockJigsawInfo.canAttach(blockJigsawInfo2,direction)) {
                                        // System.out.println("can attach");

                                        BPos blockPos3 = blockJigsawInfo2.pos;
                                        BPos blockPos4 = new BPos(relativeBlockPos.getX() - blockPos3.getX(),
                                                relativeBlockPos.getY() - blockPos3.getY(), relativeBlockPos.getZ() - blockPos3.getZ());

                                        //System.out.println(blockJigsawInfo.pos + " -> " + blockPos4);
                                        BlockBox box2;
                                        if(size1 == null){
                                            box2 = new BlockBox(blockPos4.getX(),blockPos4.getY(),blockPos4.getZ(),blockPos4.getX(),
                                                    blockPos4.getY(),blockPos4.getZ());
                                        }
                                        else {
                                            box2 = BlockBox.getBoundingBox(blockPos4, rotation1, BPos.ORIGIN, BlockMirror.NONE, size1);
                                        }
                                        int j1 = box2.minY;
                                        int k1 = blockPos3.getY();
                                        int l1 = y - k1 + blockJigsawInfo.getFront().getVector().getY();

                                        int i2 = minY + l1;

                                        int j2 = i2 - j1;
                                        BlockBox box3 = new BlockBox(box2.minX,box2.minY,box2.minZ,box2.maxX,box2.maxY,box2.maxZ);
                                        box3.move(0, j2, 0);
                                        BPos blockpos5 = blockPos4.add(0, j2, 0);

                                        // "expansion hack"
                                        if (l > 0) {
                                            int k2 = Math.max(l + 1, box3.maxY - box3.minY);
                                            box3.maxY = box3.minY + k2;
                                        }

                                        if (isNotEmpty(mutableobject1,box3)) {
                                            mutableobject1.fullBoxes.add(new BlockBox(box3.minX,box3.minY,box3.minZ,
                                                    box3.maxX+1,box3.maxY+1,box3.maxZ+1));

                                            Piece piece2 = new Piece(jigsawpiece1,blockpos5,box3,rotation1,depth+1);
                                            this.pieces.add(piece2);

                                            if(depth+1<= this.maxDepth){
                                                piece2.setVoxelShape(mutableobject1);
                                                this.placing.add(piece2, placementPriority);
                                                // this.placing.add(var56, var30 aka placementPriority);
                                                // final SequencedPriorityIterator<JigsawPlacement.PieceState> placing = new SequencedPriorityIterator();

                                                //System.out.println("placed piece: " + piece2.getName());
                                                //System.out.println("this.placing.hasNext(): " + this.placing.hasNext());
                                            }
                                            continue label139;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        private int maxHeightOfList(List<BlockJigsawInfo> list,BlockBox box){
            int max = 0;
            for (BlockJigsawInfo blockJigsaw :list) {
                BlockDirection dirtmp = blockJigsaw.getFront();
                BPos relativetmp = new BPos(blockJigsaw.pos.getX() + dirtmp.getVector().getX(),
                        blockJigsaw.pos.getY() + dirtmp.getVector().getY(),
                        blockJigsaw.pos.getZ() + dirtmp.getVector().getZ());

                if (!box.contains(relativetmp)) {
                    continue;
                }
                else {
                    max = Math.max(PillagerOutpostPoolYMax.get(blockJigsaw.nbt.poolType), max);
                }
            }

            return max;
        }

        private boolean isNotEmpty(VoxelShape voxelShape, BlockBox box1) {
            if(box1.minX<voxelShape.getX().get(0) || box1.minY<voxelShape.getY().get(0) || box1.minZ<voxelShape.getZ().get(0)
                    || box1.maxX>=voxelShape.getLastX() || box1.maxY>=voxelShape.getLastY() || box1.maxZ>=voxelShape.getLastZ())
                return false;

            for (BlockBox fullBox: voxelShape.fullBoxes){
                if(intersects2(box1,fullBox)){
                    return false;
                }
            }
            return true;
        }

        public boolean intersects2(BlockBox box1, BlockBox box) {
            return box1.maxX >= box.minX && box1.minX < box.maxX && box1.maxZ >= box.minZ && box1.minZ < box.maxZ && box1.maxY >= box.minY && box1.minY < box.maxY;
        }
    }

    private static ArrayList<Integer> getShuffledTemplatesFromPool(List<Pair<Integer, Integer>> pool, JRand rand) {
        ArrayList<Integer> result = getTemplatesFromPool(pool);
        rand.shuffle(result);
        return result;
    }

    private static ArrayList<Integer> getTemplatesFromPool(List<Pair<Integer, Integer>> pool) {
        ArrayList<Integer> result = new ArrayList<>();

        for(Pair<Integer, Integer> template : pool) {
            for(int i = 0; i < template.getSecond(); i++) {
                result.add(template.getFirst());
            }
        }

        return result;
    }
}