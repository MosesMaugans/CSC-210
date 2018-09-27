import org.junit.Assert;
import org.junit.Test;

import bool_exp.ASTNode;
import bool_exp.BoolSatParser;

public class ASTNodeTest {

    @Test
    public void testChildrenAreNull() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        ASTNode left = root.child1;
        ASTNode right = root.child2;
        Assert.assertTrue(left.child1 == null);
        Assert.assertTrue(left.child2 == null);
        Assert.assertTrue(right.child1 == null);
        Assert.assertTrue(right.child2 == null);
    }

    @Test
    public void testChildrenAreNodes() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        Assert.assertTrue(root.child1 != null);
        Assert.assertTrue(root.child2 != null);
    }

    @Test
    public void testIsId() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        Assert.assertTrue(root.child1.isId());
        Assert.assertTrue(root.child2.isId());
    }

    @Test
    public void testIsntId() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        Assert.assertFalse(root.isId());
    }

    @Test
    public void testIsNand() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        Assert.assertTrue(root.isNand());
    }

    @Test
    public void testIsntNand() {
        ASTNode root = BoolSatParser.parse("a NAND a");
        Assert.assertFalse(root.child1.isNand());
        Assert.assertFalse(root.child2.isNand());
    }

    @Test
    public void testCorrectId() {
        ASTNode root = BoolSatParser.parse("a NAND b");
        Assert.assertTrue(root.child1.getId().equals("a"));
        Assert.assertTrue(root.child2.getId().equals("b"));
    }
}
