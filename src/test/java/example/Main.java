package example;

import com.seedfinding.mccore.rand.ChunkRand;
import kludwisz.outpost.generator.PillagerOutpostGenerator;

public class Main {
    public static void main(String[] args) {
        PillagerOutpostGenerator generator = new PillagerOutpostGenerator();
        generator.generate(2958979063315823006L, -60, -204, new ChunkRand());
        generator.printPieces();
    }
}
