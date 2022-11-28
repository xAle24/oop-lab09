package it.unibo.oop.workers02;

import java.util.stream.IntStream;

public class MultiThreadSumMatrix implements SumMatrix {
    final int nthread;

    public MultiThreadSumMatrix(final int n) {
        super();
        this.nthread = n;
        if (this.nthread < 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double sum(final double[][] matrix) {
        final int size = matrix.length % nthread + matrix.length / nthread;
        return IntStream
                .iterate(0, start -> start + size)
                .limit(nthread)
                .parallel()
                .mapToDouble(start -> {
                    double result = 0;
                    for (int i = start; i < matrix.length && i < start + size; i++){
                        for (final double d : matrix[i]){
                            result += d;
                        }
                    }
                    return result;
                })
                .sum();
    }
}
