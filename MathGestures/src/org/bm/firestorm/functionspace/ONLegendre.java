package org.bm.firestorm.functionspace;

/**
 * Legendre polynomials.
 */
public class ONLegendre implements OrthogonalPolynomials {

    public static final ONLegendre INSTANCE = new ONLegendre();

    private ONLegendre() {}

    static final double[] range = {-1.0, 1.0};

    public double[] getRange() {
        return range;
    }

    public Function getFunction(final int n) {
        return new Function() {

            public double getValue(double x) {
                return ONLegendre.this.getValue(n, x);
            }
            {
            }
        };
    }
    {
    }

    public double getValue(int n, double x) {
        return recurrence(n, x) / Math.sqrt(2 / (2 * n + 1.0));
    }

    public double recurrence(int n, double x) {
        switch (n) {
            case 0:
                return 1;
            case 1:
                return x;
            case 2:
                return (3 * x * x - 1) / 2.0;
            case 3:
                return (5 * x * x * x - 3 * x) / 2.0;
            case 4:
                return (35 * x * x * x * x - 30 * x * x + 3) / 8.0;
            default:
                return ((2.0 * n - 1.0) * x * recurrence(n - 1, x) - (n - 1.0) * recurrence(n - 2, x)) / (double) n;
        }
    }
}
