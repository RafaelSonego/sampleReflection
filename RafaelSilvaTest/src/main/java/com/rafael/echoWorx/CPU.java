package com.rafael.echoWorx;

import com.rafael.echoWorx.pojo.Register;

public class CPU {

	public void set(Register register, byte value) {
		register.setValue(value);
	}

	public void set(Register registerOne, Register registerTwo) {
		registerOne.setValue(registerTwo.getValue());
	}

	public void add(Register registerOne, Register registerTwo) {
		registerOne.setValue((byte) (registerOne.getValue() + registerTwo.getValue()));
	}

	public void add(Register register, byte value) {
		register.setValue((byte) (register.getValue() + value));
	}

	public void subt(Register registerOne, Register registerTwo) {
		registerOne.setValue((byte) (registerOne.getValue() - registerTwo.getValue()));
	}

	public void subt(Register register, byte value) {
		register.setValue((byte) (register.getValue() - value));
	}

	public void lshift(Register registerOne, Register registerTwo) {
	}

	public void lshift(Register register, byte value) {
	}

	public void read(Register registerOne, Register registerTwo) {

	}

	public void read(Register register, byte value) {

	}

	public void write(Register registerOne, Register registerTwo) {
		
	}

	public void write(Register register, byte value) {
		
	}

}
