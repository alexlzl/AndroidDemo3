package weeho.com.petim.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangkui on 2017/4/20.
 */

public class AchievementBean implements Serializable {
    public Correct correct;
    public Training training ;

    public Correct getCorrect() {
        return correct;
    }

    public void setCorrect(Correct correct) {
        this.correct = correct;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public static class Correct implements Serializable{
        private List<CorrectBean> list;

        public List<CorrectBean> getList() {
            return list;
        }

        public void setList(List<CorrectBean> list) {
            this.list = list;
        }
    }

    public static class CorrectBean{
        private String coname;
        private String courl;
        public String getConame() {
            return coname;
        }

        public void setConame(String coname) {
            this.coname = coname;
        }

        public String getCourl() {
            return courl;
        }

        public void setCourl(String courl) {
            this.courl = courl;
        }
    }

    private static class TrainingBean implements Serializable{
        private String trname;
        private String trurl;

        public String getTrname() {
            return trname;
        }

        public void setTrname(String trname) {
            this.trname = trname;
        }

        public String getTrurl() {
            return trurl;
        }

        public void setTrurl(String trurl) {
            this.trurl = trurl;
        }
    }
    public static class Training implements Serializable{
        private List<TrainingBean> list;

        public List<TrainingBean> getList() {
            return list;
        }

        public void setList(List<TrainingBean> list) {
            this.list = list;
        }
    }
}
