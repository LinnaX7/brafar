package repairjava.entity.sar.config;

import repairjava.entity.program.ProgramBuilder;
import repairjava.entity.program.TesterBuilder;

public class Config {
    private ProgramBuilder buggyProgram;
    private ProgramBuilder correctProgram;
    private String methodToFix;
    private TesterBuilder testerProgram;

    public void setBuggyProgram(ProgramBuilder buggyProgram) {
        this.buggyProgram = buggyProgram;
    }

    public ProgramBuilder getBuggyProgram() {
        return buggyProgram;
    }

    public void setMethodToFix(String methodToFix) {
        this.methodToFix = methodToFix;
    }

    public String getMethodToFix() {
        return methodToFix;
    }

    public void setCorrectProgram(ProgramBuilder correctProgram) {
        this.correctProgram = correctProgram;
    }

    public ProgramBuilder getCorrectProgram() {
        return correctProgram;
    }

    public void setTesterProgram(TesterBuilder testerProgram) {
        this.testerProgram = testerProgram;
    }

    public TesterBuilder getTesterProgram() {
        return testerProgram;
    }

    /*只要方法名*/
    public String getFaultyMethodSignature(){
        return getMethodToFix().substring(0, getMethodToFix().indexOf('@'));
    }

    public String getClassName(){
        return getMethodToFix().substring(getMethodToFix().indexOf('@')+1);
    }
}

