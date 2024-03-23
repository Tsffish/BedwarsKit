package github.tsffish.bedwarskit.util;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;

/**
 * A Addon for BedwarsRel, Added some features to BedwarsRel
 * github.com/Tsffish/BedwarsKit
 *
 * @author Tsffish
 */
public class PacketUtil {
    public static class PacketReader {
        public static StructureModifier<Float> getFloat(PacketContainer pc) {
            return pc.getFloat();
        }

        public static StructureModifier<Integer> getIntegers(PacketContainer pc) {
            return pc.getIntegers();
        }
    }
    public static class PacketWriter {
        public static void writeFloat(StructureModifier<Float> smF, float value) {
            smF.write(0, value);
        }
    }
}
