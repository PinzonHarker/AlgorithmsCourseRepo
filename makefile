SHELL:= /bin/zsh
JXX= javac
JXXFLAGS= -g

DEPS= src/PercolationStats
TARGET= src/Percolation

$(TARGET).zip: all

$(TARGET).zip: $(TARGET).java $(DEPS).java
	zip $@ $^

.PHONY: clean
clean:
	rm -f *.class

