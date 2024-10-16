public class MiniFloat {

    public static float miniFloatFromString(String s) {
        int S = 1;
        if (s.charAt(0) == '1') {
            S = -1;
        }
        int E = 0;
        for (int i = 1; i <= 4; i++) {
            E = E * 2 + (s.charAt(i) - '0');
        }
        if (s.charAt(1) == '1') {
            E -= 1 << 4;
        }
        float M = 1.0f, p = 0.5f;
        for (int i = 5; i <= 7; i++) {
            M += p * (s.charAt(i) - '0');
            p /= 2;
        }
        if (E > 0) {
            while (E != 0) {
                M *= 2;
                E--;
            }
        } else {
            while (E != 0) {
                M /= 2;
                E++;
            }
        }
        return S * M;
    }

    public int numIntegralMiniFloats() {
        String[] seq = getValidMiniFloatBitSequences();
        int cnt = 0;
        for (int i = 0; i < 256; i++) {
            float v = miniFloatFromString(seq[i]);
            if (v == (int) v)
                cnt++;
        }
        return cnt;
    }

    public String[] getValidMiniFloatBitSequences() {
        String[] sequences = new String[256];
        String temp;
        for (int i = 0; i < 256; i++) {
            sequences[i] = "00000000";
            temp = Integer.toBinaryString(i);
            sequences[i] = sequences[i].substring(0, 8 - temp.length()) + temp;
        }
        return sequences;
    }
}
