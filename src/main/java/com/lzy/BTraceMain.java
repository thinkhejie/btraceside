package com.lzy;

import com.sun.btrace.BTraceUtils;

import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.*;

import java.util.Map;

import static com.sun.btrace.BTraceUtils.*;
import static com.sun.btrace.BTraceUtils.Reflective.field;

@BTrace
public class BTraceMain {
    /**
     * @param probeClass
     * @param probeMethod
     * @param self
     */
    //@OnMethod(clazz = "java.util.HashMap", location = @Location(value = Kind.LINE, line = 678, clazz = "/.*/", method = "/.*/"))
    @OnMethod(clazz = "java.util.HashMap", method = "resize", location = @Location(value = Kind.ENTRY))
    public static void traceMapExpandCapacity(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod, @Self Object self) {
        //public static void traceMapExpandCapacity() {
        println(Strings.strcat("probeClass:", str(probeClass)));
        String point = Strings.strcat(BTraceUtils.Strings.strcat(probeClass, "."), probeMethod);//java/util/HashMap.resize
        Class clazz = classForName("java.util.HashMap");
        println(BTraceUtils.Strings.strcat(point, "======"));
        //获取实例protected变量
        Map.Entry[] table = (Map.Entry[]) get(field(clazz, "table", true), self);
        int threshold = getInt(field(clazz, "threshold", true), self);
        int size = getInt(field(clazz, "size", true), self);
        //println(Strings.strcat("newCapacity:", str(newCapacity)));
        println(Strings.strcat("table.length:", str(table.length)));
        println(Strings.strcat("size:", str(size)));
        println(Strings.strcat("threshold:", str(threshold)));
        println(Strings.strcat(point, "------------"));
    }
}
