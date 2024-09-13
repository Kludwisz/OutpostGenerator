package kludwisz.outpost.jigsaws;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.seedfinding.mccore.util.block.BlockDirection;
import com.seedfinding.mccore.util.pos.BPos;

public class PillagerOutpostJigsawBlocks {
	public static List<JigsawBlock> get(int pieceID) {
		return JIGSAW_BLOCKS.get(pieceID);
	}
	
    private static final List<List<JigsawBlock>> JIGSAW_BLOCKS = Arrays.asList(
// base_plate id = 0
			Arrays.asList(
					new JigsawBlock(2, JointType.ALIGNED, "minecraft:plate_entry", "minecraft:plate_entry", BlockDirection.WEST, BlockDirection.UP, new BPos(0,0,7), 0, 0),
					new JigsawBlock(2, JointType.ALIGNED, "minecraft:plate_entry", "minecraft:plate_entry", BlockDirection.SOUTH, BlockDirection.UP, new BPos(7,0,15), 0, 0),
					new JigsawBlock(2, JointType.ALIGNED, "minecraft:plate_entry", "minecraft:plate_entry", BlockDirection.EAST, BlockDirection.UP, new BPos(15,0,8), 0, 0),
					new JigsawBlock(3, JointType.ALIGNED, "minecraft:entrance", "minecraft:entrance", BlockDirection.NORTH, BlockDirection.UP, new BPos(7,1,14), 0, 0),
					new JigsawBlock(3, JointType.ALIGNED, "minecraft:entrance", "minecraft:entrance", BlockDirection.NORTH, BlockDirection.UP, new BPos(8,1,14), 0, 0)
			),
// feature_cage1 id = 1
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,6), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(4,0,3), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,6), 0, 0)
			),
// feature_cage2 id = 2
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,6), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(4,0,3), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,6), 0, 0)
			),
// feature_cage_with_allays id = 3
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,6), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(4,0,3), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(6,0,6), 0, 0)
			),
// feature_logs id = 4
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,0), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,6), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,0), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,6), 0, 0)
			),
// feature_plate id = 5
			Arrays.asList(
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(1,0,17), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(1,0,30), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(2,0,23), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(3,0,19), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(3,0,28), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(5,0,21), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(5,0,26), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(7,0,23), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(9,0,21), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(9,0,25), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(11,0,19), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(11,0,27), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(12,0,23), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(13,0,17), 0, 0),
					new JigsawBlock(1, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.UP, BlockDirection.NORTH, new BPos(13,0,29), 0, 0),
					new JigsawBlock(4, JointType.ALIGNED, "minecraft:plate_entry", "minecraft:plate_entry", BlockDirection.EAST, BlockDirection.UP, new BPos(15,0,7), 0, 0)
			),
// feature_targets id = 6
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,0), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,6), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(1,0,3), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(2,0,0), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(2,0,6), 0, 0)
			),
// feature_tent1 id = 7
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,5), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,5), 0, 0)
			),
// feature_tent2 id = 8
			Arrays.asList(
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(0,0,5), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,1), 0, 0),
					new JigsawBlock(4, JointType.ROLLABLE, "minecraft:feature", "minecraft:feature", BlockDirection.DOWN, BlockDirection.SOUTH, new BPos(5,0,5), 0, 0)
			),
// watchtower id = 9
			Collections.singletonList(
					new JigsawBlock(4, JointType.ALIGNED, "minecraft:entrance", "minecraft:entrance", BlockDirection.SOUTH, BlockDirection.UP, new BPos(7,1,13), 0, 0)
			),
// watchtower_overgrown id = 10
			Collections.singletonList(
					new JigsawBlock(4, JointType.ALIGNED, "minecraft:entrance", "minecraft:entrance", BlockDirection.SOUTH, BlockDirection.UP, new BPos(7,1,13), 0, 0)
			),
// empty id = 11
			Collections.emptyList()
    );
}
