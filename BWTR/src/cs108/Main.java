package cs108;


public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> bwt = BurrowsWheelerTransform.forward("Hello, World!");
        System.out.println(bwt);

        String result = BurrowsWheelerTransform.backward(bwt);
        System.out.println(result);

        String m = "ssssrs;àtsessten .hmmfffm asnsltsLlll"
                + "ssrlhhhrrr   cl lmmb ll aaii  eaaouoeçstu uuu"
                + "eeeeeeee suuu ennu ceeeeeeeo a";
        Pair<Integer, String> mBWT = new Pair<>(17, m);
        System.out.println(BurrowsWheelerTransform.backward(mBWT));
    }
}
