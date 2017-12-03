package main

import (
	"fmt"
)

func toPostfix(prefix string) string {
	var stack stringStack
	for i := len(prefix) - 1; i >= 0; i-- {
		switch ch := prefix[i]; ch {
		case '+', '-', '*', '/':
			op1 := stack.pop()
			op2 := stack.pop()
			stack.push(op1 + op2 + string(ch))
		default:
			stack.push(string(ch))
		}
	}
	return stack.peek()
}

func main() {
	fmt.Println(toPostfix("+-435"))
	fmt.Println(toPostfix("-*+ABC*-DE+FG"))
}

//----------------------------------------------------------

type stringStack struct {
	arr []string
}

func (s *stringStack) push(v string) {
	s.arr = append(s.arr, v)
}

func (s *stringStack) pop() string {
	v := s.arr[len(s.arr)-1]
	s.arr = s.arr[:len(s.arr)-1]
	return v
}

func (s *stringStack) peek() string {
	return s.arr[len(s.arr)-1]
}
