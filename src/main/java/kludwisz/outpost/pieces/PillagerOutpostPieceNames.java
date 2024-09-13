package kludwisz.outpost.pieces;

import java.util.Arrays;
import java.util.List;

public class PillagerOutpostPieceNames {
	public static String get(int pieceID) {
		return PIECENAMES.get(pieceID);
	}
	
	private static final List<String> PIECENAMES = Arrays.asList(
			"base_plate",  /*id = 0*/
			"feature_cage1",  /*id = 1*/
			"feature_cage2",  /*id = 2*/
			"feature_cage_with_allays",  /*id = 3*/
			"feature_logs",  /*id = 4*/
			"feature_plate",  /*id = 5*/
			"feature_targets",  /*id = 6*/
			"feature_tent1",  /*id = 7*/
			"feature_tent2",  /*id = 8*/
			"watchtower",  /*id = 9*/
			"watchtower_overgrown",  /*id = 10*/
			"empty"  /*id = 11*/
	);
}
