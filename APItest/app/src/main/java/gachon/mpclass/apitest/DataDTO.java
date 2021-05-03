package gachon.mpclass.apitest;

import java.util.ArrayList;
import java.util.HashMap;

public class DataDTO {

    DataDTO(ArrayList<ArrayList<Item>> list){
        this.list = list;
    }

    /**
     * Features
     * - idx, id, name, stageId, elapsedTime
     */
    private ArrayList<ArrayList<Item>> list;

    public ArrayList<ArrayList<Item>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<Item>> list) {
        this.list = list;
    }

    public class Item {
        Item(int idx, int id, String name, int stageId, int elapsedTime){
            this.idx = idx;
            this.id = id;
            this.name = name;
            this.stageId = stageId;
            this.elapsedTime = elapsedTime;
        }
        private int idx;
        private int id;
        private String name;
        private int stageId;
        private int elapsedTime;

        public int getIdx() {
            return idx;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStageId() {
            return stageId;
        }

        public int getElapsedTime() {
            return elapsedTime;
        }
    }
}


