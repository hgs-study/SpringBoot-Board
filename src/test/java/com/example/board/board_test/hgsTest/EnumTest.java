package com.example.board.board_test.hgsTest;

import com.example.board.board_test.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@WebMvcTest(controllers = HelloController.class)
public class EnumTest {

    @Test
    public  void main() {
        System.out.printf("t : "+t.HAHA.hihi2);

    }


     enum t {
        HAHA ("HAHA2",true),
        HIHI ( "HIHI2",false);

        String hihi2 = "";
        boolean b;

         t(String hihi2, boolean b) {
             this.hihi2 =hihi2;
             this.b = b;
         }
     }
}
