package com.os.annotation.test.response;

import java.io.IOException;

/**
 * @author QuangNN
 */
public interface DataIterator<T> {
    T current();
    boolean next() throws IOException;
}
