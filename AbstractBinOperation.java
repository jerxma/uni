package expression;
public abstract class AbstractBinOperation extends AbstractOperand {
    protected AbstractOperand first;
    protected AbstractOperand second;
    protected int hash;
    protected AbstractBinOperation(AbstractOperand first, AbstractOperand second) {
        this.first = first;
        this.second = second;
        hash = 17 * (first.hashCode() + 191 * second.hashCode()) + getClass().hashCode();
    }

    protected abstract boolean isCommutative();

    protected abstract int getPriority();

    @Override
    public String toString() {
        return new StringBuilder()
                .append('(')
                .append(first.toString())
                .append(' ')
                .append(this.getSign())
                .append(' ')
                .append(second.toString())
                .append(')').toString();
    }

    @Override 
    public boolean equals(Object obj) {
        return super.equals(obj)
                && (((AbstractBinOperation) obj).first.equals(this.first)) 
                && (((AbstractBinOperation) obj).second.equals(this.second));
    }

    @Override
    public int hashCode() {
        return hash;
    }

    protected abstract int calculate(int first, int second);

    @Override
    public int evaluate(int val) {
        return calculate(first.evaluate(val), second.evaluate(val));
    }

    @Override
    public int evaluate(int x, int y, int z) {
            return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z)); 
    }

    protected abstract String getSign();



    private void appendOperand(StringBuilder str, boolean checkCommutativity, AbstractOperand op) {
        boolean brackets = bracketsNeeded(op, checkCommutativity);
        if (brackets) {
            str.append('(');
        }
        str.append(op.toMiniString());
        if (brackets) {
            str.append(')');
        }
    }

    private boolean bracketsNeeded(AbstractOperand op, boolean checkCommutativity) {
        if (op instanceof AbstractBinOperation) {
            AbstractBinOperation binOp = (AbstractBinOperation) op;
            return checkPriority(binOp) 
                    || checkCommutativity && checkCommutativity(binOp);
        }
        return false;
    }

    private boolean checkPriority(AbstractBinOperation binOp) {
        return binOp.getPriority() < this.getPriority();
    }

    private boolean checkCommutativity(AbstractBinOperation binOp) {
        return !(binOp.isCommutative() && this.isCommutative()) 
                && binOp.getPriority() <= this.getPriority();
        
    } 

    @Override
    public String toMiniString() {
        StringBuilder tmpString = new StringBuilder();
        
        appendOperand(tmpString, false, first);

        tmpString.append(' ')
                .append(getSign())
                .append(' ');
        
        appendOperand(tmpString, true, second);
        
        return tmpString.toString();
    }

}