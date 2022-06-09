SHELL:= /bin/zsh
CXX=g++
CXXFLAGS= -g

DEPS= foo.o bar.o
TARGET= main

$(TARGET).x: $(DEPS)
  $(CXX) $(CXXFLAGS) $@.cpp $< -o main.x 

%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $<

.PHONY: clean
clean:
	rm -f *.o *~ *.x

