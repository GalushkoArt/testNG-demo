import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import static controller.TestedClass.*;

public class SimpleTest {

    @Test
    public void startTest() {
        int a = 2, b = 2;
        int res = sum(2, 2);
        Assert.assertEquals(res, 4);
    }

    @Test(enabled = true)
    public void strangeTest() {
        int a = 0, b = 23;
        int res = sum(a, b);
        Assert.assertEquals(res, 24, "strange test failed!");
    }

    @Test(groups = {"sum"}, priority = 2)
    public void addTest() {
        int a = -12, b = 3252;
        int res = sum(a, b);
        Assert.assertEquals(res, 3240, "adding failed!");
    }

    @Test(groups = {"multi"}, priority = 2)
    public void multiTest() {
        int a = 22, b = 19;
        int res = multi(a, b);
        Assert.assertEquals(res, 418, "multiplication failed!");
    }

    @Test(groups = {"sum", "multi"}, priority = 3)
    public void complicatedTest() {
        int a = 2, b = 3, c = 5;
        int res = multi(sum(a, b), c);
        Assert.assertEquals(res, 25, "complicated test failed!");
    }

    @Test(groups = {"sum"})
    @Parameters(value = {"a", "b", "res"})
    public void lazyTest(int a, int b, int res) {
        Assert.assertEquals(sum(a, b), res, "lazy test failed!");
    }
}
