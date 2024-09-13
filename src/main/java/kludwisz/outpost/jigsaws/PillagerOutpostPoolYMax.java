package kludwisz.outpost.jigsaws;

public class PillagerOutpostPoolYMax {
    public static int get(int poolID) {
        return OUTPOST_Y_MAX[poolID];
    }

    private static final int[] OUTPOST_Y_MAX = new int[] {
        30,  /* base_plates id = 0 */
        4,   /* features id = 1 */
        4,   /* feature_plates id = 2 */
        21,  /* towers id = 3 */        // TODO might be 23 because of overgrown, check
        0    /* empty id = 4 */
    };
}
