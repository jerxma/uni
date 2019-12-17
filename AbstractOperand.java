package expression;

public abstract class AbstractOperand implements Expression, TripleExpression {
    
    // @Override
    // public boolean equals(Object obj) {
    //     if (obj == null) {
    //         return false;
    //     }
    //     if (obj == this) {
    //         return true;
    //     }
    //     return (obj.getClass() == this.getClass()); 
    // }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }

    // protected abstract boolean isCommutative();

    // protected abstract int getPriority();

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public abstract int evaluate(int val);

    public abstract int evaluate(int x, int y, int z);
}