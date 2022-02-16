package zajednicko;

import zajednicko.db.FusekiManager;

import java.io.IOException;

public class ZajednickoMain {

    public static void main(String[] args) throws IOException {
        FusekiManager fm = new FusekiManager();
        fm.testUpload();
    }
}
