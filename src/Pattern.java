

public class Pattern {

    private String pattern;

    public Pattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.pattern != null ? this.pattern.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pattern other = (Pattern) obj;
        if ((this.pattern == null) ? (other.pattern != null) : !this.pattern.equals(other.pattern)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return pattern;
    }

}
