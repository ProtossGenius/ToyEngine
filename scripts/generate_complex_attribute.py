#!python3
from attribs import ATTRIB_LIST
from datetime import datetime
print(f"""
package com.suremoon.game.door.attribute;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

/**
 * @Note: this file product by scripts/generate_complex_attribute.py, you should change scripts to fix this file.
 * , Or, your another change will be cover. @Author: ProtossGenius @Time: {datetime.now().strftime("%Y-%m-%d %H:%M:%S")}
 */
public class ComplexAttribute implements SerializeAble {{
    AttributeAdapter basic;
    AttributeAdapter max;
    AttributeAdapter cur;
    BuffManager buffManager;

    public ComplexAttribute(AttributeAdapter basic) {{
        this.basic = basic;
        this.max = new AttributeAdapter(basic);
        this.cur = new AttributeAdapter(basic);
        buffManager = new BuffManager(basic, max);
    }}

    public BuffManager getBuffManager() {{
        return buffManager;
    }}

    @Override
    public void parseFromBytes(ByteStream byteStream) {{
        basic.parseFromBytes(byteStream);
        max.parseFromBytes(byteStream);
        cur.parseFromBytes(byteStream);
    }}

    @Override
    public byte[] encodeToBytes() {{
        return CJDeal.ByteArrayConnect(basic.encodeToBytes(), max.encodeToBytes(), cur.encodeToBytes());
    }}
""")

for key, value in ATTRIB_LIST.items():
    print(f"""
    /**
     * 设置Basic {value}.
     */
    public void setBasic{key}(double value) {{
        this.basic.set{key}(value);
    }}

    /**
     * 获得Basic {value}.
     */
    public double getBasic{key}() {{
        return this.basic.get{key}();
    }}
    /**
     * 设置Max {value}.
     */
    public void setMax{key}(double value) {{
        this.max.set{key}(value);
    }}

    /**
     * 获得Max {value}.
     */
    public double getMax{key}() {{
        return this.max.get{key}();
    }}
    /**
     * 设置 {value}.
     */
    public void set{key}(double value) {{
        this.cur.set{key}(value);
    }}

    /**
     * 获得 {value}.
     */
    public double get{key}() {{
        return this.cur.get{key}();
    }}
""")

print("}")