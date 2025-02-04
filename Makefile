qrun:
	gradle run

prebuild:
	python3 ./scripts/generate_bag.py >door/src/main/java/com/suremoon/game/door/units_itf/BagMgrItf.java	
	python3 ./scripts/generate_attributes.py >./door/src/main/java/com/suremoon/game/door/attribute/AttributeAdapter.java
	python3 ./scripts/generate_complex_attribute.py >./door/src/main/java/com/suremoon/game/door/attribute/ComplexAttribute.java
