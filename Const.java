package expression;
public class Const extends AbstractOperand {

    private Number val;

    public Const(double val) {
        this.val = val;
    }

    public Const(int val) {
        this.val = val;
    }
    
    @Override
    public boolean equals(Object obj) {
        return  super.equals(obj)
                && ((Const) obj).val.equals(this.val);
    }

    @Override
    public int evaluate(int val) {
        return this.val.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.val.intValue();
    }

    

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }
}