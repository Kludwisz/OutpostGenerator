package kludwisz.outpost.pieces;

import com.seedfinding.mccore.util.pos.BPos;

import java.util.Arrays;
import java.util.List;

public class PillagerOutpostStructureSize {
	public static BPos get(int pieceID) {
		return STRUCTURE_SIZE.get(pieceID);
	}
	
    private static final List<BPos> STRUCTURE_SIZE = Arrays.asList(
			new BPos(16,30,16),      /* 0 base_plate */
			new BPos(7,4,7),         /* 1 feature_cage1 */
			new BPos(7,4,7),         /* 2 feature_cage2 */
			new BPos(7,4,7),         /* 3 feature_cage_with_allays */
			new BPos(6,3,7),         /* 4 feature_logs */
			new BPos(16,4,32),       /* 5 feature_plate */
			new BPos(3,3,7),         /* 6 feature_targets */
			new BPos(6,4,7),         /* 7 feature_tent1 */
			new BPos(6,4,7),         /* 8 feature_tent2 */
			new BPos(15,21,15),      /* 9 watchtower */
			new BPos(15,23,15),      /* 10 watchtower_overgrown */
			new BPos(0, 0, 0)        /* 11 empty */
    );
}

