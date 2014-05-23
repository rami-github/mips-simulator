package datapath;

import java.util.Hashtable;

import peripherals.ProgramCounter;

import memory.*;
import registers.RegisterManager;
import alu.ALU;

public abstract class AbstractDatapath {
	Memory hardwareMemory;
	DataMemory dataMemory;
	InstructionMemory instructionMemory;
	RegisterManager registerManager;
	ALU alu;
	ProgramCounter pc;
	
	public AbstractDatapath(String[] instructions, String[] data, int programOffset) {
		hardwareMemory = new Memory();
		instructionMemory = new InstructionMemory(hardwareMemory, instructions, programOffset);
		dataMemory = new DataMemory(hardwareMemory, data);
		pc = new ProgramCounter();
		pc.setCounter(programOffset);
	}
	
	public Hashtable<String, String> getRegisterContents() {
		return registerManager.getRegisterContents();
	}

	public Hashtable<Long, String> getDataMemoryContents() {
		return dataMemory.getMemoryContents();
	}

	public Hashtable<Long, String> getInstructionMemoryContents() {
		return instructionMemory.getMemoryContents();
	}
	
	public void setMemoryContent(long address, String value) {
		hardwareMemory.write(address, value);
	}
	
	public int getProgramCounterValue() {
		return pc.getCounter();
	}

	public abstract Hashtable<String, String> getPipelineRegistersContents();
	public abstract boolean nextStep();
	public abstract boolean previousStep();	
}
