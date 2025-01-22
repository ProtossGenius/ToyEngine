#!python3
from attribs import ATTRIB_LIST
from datetime import datetime
print(f"""
package com.suremoon.game.door.attribute;
/**
 * @Note: this file product by scripts/generate_attributes.py, you should change scripts to fix this file.
 AttributeAdapter, Or, your another change will be cover. @Author: ProtossGenius @Time: {datetime.now().strftime("%Y-%m-%d %H:%M:%S")}
  */
public class AttributeAdapter extends SMAttribute {{

""")
index = 0
for key, value in ATTRIB_LIST.items():
    print(f"""
     /**
      * {key} {value}.
      */
     public static final int {key}= {index};
""")
    index = index + 1

print(f"""
    public AttributeAdapter() {{
        super({index});
    }}

    public AttributeAdapter(AttributeAdapter another) {{
        super(another);
    }}
""")
for key, value in ATTRIB_LIST.items():
    print(f"""
    /**
     * 设置 {value}.
     */
    public void set{key}(double value) {{
        setAttribute({key}, value);
    }}

    /**
     * 获得 {value}.
     */
    public double get{key}() {{
        return getAttribute({key});
    }}
           """)

print("}")