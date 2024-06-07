package utils;

import main.Game;

public class Constants {

    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT * Game.SCALE);
        }

        public static class URMButts{
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE);
        }

        public static class VolumeButtons{
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            public static final int SLIDER_DEFAULT_WIDTH = 215;


            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    public static class DIRECTION{
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
        }
    public static class PlayerConstants{
        public static final int FALL = 0;
        public static final int IDLE = 1;
        public static final int THROW_SWORD = 6;
        public static final int HIT = 3;
        public static final int JUMP = 2;
        public static final int AIR_ATK1 = 5;
        public static final int ATK1 = 4;
        public static final int RUN = 7;
        public static final int GROUND_ATK = 8;
        public static final int ATK2 = 9;
        public static final int AIR_ATK2 = 10;
        public static final int AIR_ATK3 = 11;

        public static int GetAnimAmount(int action){
            switch (action) {
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case THROW_SWORD:
                    return 3;
                case RUN:
                    return 6;
                case AIR_ATK1:
                    return 3;
                case AIR_ATK2:
                    return 3;
                case AIR_ATK3:
                    return 3;
                case ATK1:
                    return 3;
                case ATK2:
                    return 3;
                case GROUND_ATK:
                    return 2;
                default:
                    return 1;
            }
        }
    }
}
