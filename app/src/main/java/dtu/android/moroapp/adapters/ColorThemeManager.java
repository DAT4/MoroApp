package dtu.android.moroapp.adapters;

import dtu.android.moroapp.R;
import dtu.android.moroapp.Theme;

public class ColorThemeManager {

    int backgroud;
    int icon;

   public ColorThemeManager(Theme theme){
       switch (theme){
           case BLUE:
               this.backgroud = R.color.colorBackgroundBlue;
               this.icon = R.color.colorIconBlue;
               break;
           case GREEN:
               this.backgroud = R.color.colorBackgroundGreen;
               this.icon = R.color.colorIconGreen;
               break;
           case ORANGE:
               this.backgroud = R.color.colorBackgroundOrange;
               this.icon = R.color.colorIconOrange;
               break;
           default:

       }
   }

    public int getBackgroud() {
        return backgroud;
    }

    public int getIcon() {
        return icon;
    }
}
