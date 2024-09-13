package kludwisz.outpost.jigsaws;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.seedfinding.mccore.util.data.Pair;

public class PillagerOutpostPools {
	
	public static List<Pair<Integer, Integer>> get(int poolID) {
		if (poolID < 0 || poolID > OUTPOST_POOLS.size())
			return null;
		return OUTPOST_POOLS.get(poolID);
	}
	
	public static int getFallbackID(int poolID) {
		return FALLBACK_POOLS.get(poolID);
	}
	
	private static final List<Integer> FALLBACK_POOLS = Arrays.asList(
			4,  /* base_plates id = 0 */
			4,  /* features id = 1 */
			4,  /* feature_plates id = 2 */
			4,  /* towers id = 3 */
			4  	/* empty id = 4 */
	);

    private static final List< List<Pair<Integer, Integer>> > OUTPOST_POOLS = Arrays.asList(
			/* base_plates id = 0 */ Collections.singletonList(
					new Pair<>(0, 1)
			),

			/* features id = 1 */ List.of(
					new Pair<>(1, 1),
					new Pair<>(2, 1),
					new Pair<>(3, 1),
					new Pair<>(4, 1),
					new Pair<>(7, 1),
					new Pair<>(8, 1),
					new Pair<>(6, 1),
					new Pair<>(11, 6)
			),

			/* feature_plates id = 2 */ Collections.singletonList(
					new Pair<>(5, 1)
			),

			/* towers id = 3 */ Collections.singletonList(
					new Pair<>(9, 1)
			),

			/* empty id = 4 */ Collections.singletonList(
					new Pair<>(11, 0)
			)
    );
}
