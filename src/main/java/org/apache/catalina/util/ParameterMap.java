package org.apache.catalina.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于存储请求参数
 * 当locked==true时，不能修改、删除和添加参数值。任何时候都可以读取。
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class ParameterMap extends HashMap {
    private boolean locked = false;

    public ParameterMap() {
        super();
    }

    public ParameterMap(Map m) {
        super(m);
    }

    public ParameterMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ParameterMap(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean isLocked() {
        return this.locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private static final StringManager sm =
        StringManager.getManager("org.apache.catalina.util");

    public void clear() {
        if (locked) {
            throw new IllegalStateException(sm.getString("parameterMap.locked"));
        }
        super.clear();
    }

    public Object put(Object key, Object value) {
        if (locked) {
            throw new IllegalStateException(
                sm.getString("parameterMap.locked")
            );
        }
        return super.put(key, value);
    }

    public void putAll(Map map) {
        if (locked) {
            throw new IllegalStateException(
                sm.getString("parameterMap.locked")
            );
        }
        super.putAll(map);
    }

    public Object remove(Object key) {
        if (locked) {
            throw new IllegalStateException(
                sm.getString("parameterMap.locked")
            );
        }
        return super.remove(key);
    }

}
