BASE_NAME = BugTrackingUML
D2_FLAGS =

.PHONY: all clean

run:
	d2 $(D2_FLAGS) $(BASE_NAME).d2

build:
	d2 $(D2_FLAGS) $(BASE_NAME).d2 $(BASE_NAME).png

fmt:
	d2 fmt $(BASE_NAME).d2

clean:
	rm -rf $(BASE_NAME).svg $(BASE_NAME).png
