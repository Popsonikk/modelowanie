package main.bussinessLogic;

import main.database.TableGenerator;

public class InitBaseLogic {
    private final TableGenerator tableGenerator;

    public TableGenerator getTableGenerator() {
        return tableGenerator;
    }

    public InitBaseLogic(TableGenerator tableGenerator) {
        this.tableGenerator = tableGenerator;
    }
}
