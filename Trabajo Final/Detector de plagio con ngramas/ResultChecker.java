public class ResultChecker {
    private boolean[] result;

    public ResultChecker(int n) {
        this.result = new boolean[n];
    }

    public boolean[] getResult() {
        return result;
    }

    public int getResultSize() {
        return result.length;
    }

    public boolean getResultAt(int index) {
        if (index >= 0 && index < result.length) {
            return result[index];
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }

    public void setResultAt(int index, boolean value) {
        if (index >= 0 && index < result.length) {
            this.result[index] = value;
        }
    }
    
    public String resultPlagio(int pos) {
    	if(result[pos]) {
    		return " plagio encontrado\n";
    	}
    	return " plagio no encontrado\n";
    }
}
