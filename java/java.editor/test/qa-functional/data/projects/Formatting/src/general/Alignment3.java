/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.Serializable;

/**
 *
 * @author jp159440
 */
public class Alignment3 implements Runnable,
        Serializable {

    String[] a = new String[]{"a",
        "b"
    };

    public
            void m() {
        System.getProperty("key",
                "def");

        System.out.println(1 == 1
                ? "yes"
                : "no");
        for (int i = 0;
                i < 10;
                i++) {
            System.out.println(i);
        }
    }

    public
            void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
