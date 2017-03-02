package org.example.fire_test.adapters;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by takuya on 2/26/17.
 */

public class FlickTouchListener implements View.OnTouchListener{
    private float startTouchX;
    private float startTouchY;

    private float nowTouchedX;
    private float nowTouchedY;

    private float adjust = 120;

    @Override
    public boolean onTouch(View v, MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startTouchX = event.getX();
                startTouchY = event.getY();
                break;

            case MotionEvent.ACTION_UP:
                nowTouchedX = event.getX();
                nowTouchedY = event.getY();

                if( startTouchY > nowTouchedY ){
                    if( startTouchX > nowTouchedX ){
                        if( ( startTouchY - nowTouchedY ) > ( startTouchX - nowTouchedX ) ){
                            if( startTouchY > nowTouchedY + adjust ){
                                Log.v( "Flick", "--上" );
                                // 上フリック時の処理を記述する
                            }
                        }
                        else if( ( startTouchY - nowTouchedY ) < ( startTouchX - nowTouchedX ) ){
                            if( startTouchX > nowTouchedX + adjust ){
                                Log.v( "Flick", "--左" );
                                // 左フリック時の処理を記述する
                            }
                        }
                    }
                    else if( startTouchX < nowTouchedX ){
                        if( ( startTouchY - nowTouchedY ) > ( nowTouchedX - startTouchX ) ){
                            if( startTouchY > nowTouchedY + adjust ){
                                Log.v( "Flick", "--上" );
                                // 上フリック時の処理を記述する
                            }
                        }
                        else if( ( startTouchY - nowTouchedY ) < ( nowTouchedX - startTouchX ) ){
                            if( startTouchX < nowTouchedX + adjust ){
                                Log.v( "Flick", "--右" );
                                // 右フリック時の処理を記述する
                            }
                        }
                    }
                }
                else if( startTouchY < nowTouchedY ){
                    if( startTouchX > nowTouchedX ){
                        if( ( nowTouchedY - startTouchY ) > ( startTouchX - nowTouchedX ) ){
                            if( startTouchY < nowTouchedY + adjust ){
                                Log.v( "Flick", "--下" );
                                // 下フリック時の処理を記述する
                            }
                        }
                        else if( ( nowTouchedY - startTouchY ) < ( startTouchX - nowTouchedX ) ){
                            if( startTouchX > nowTouchedX + adjust ){
                                Log.v( "Flick", "--左" );
                                // 左フリック時の処理を記述する
                            }
                        }
                    }
                    else if( startTouchX < nowTouchedX ){
                        if( ( nowTouchedY - startTouchY ) > (  nowTouchedX - startTouchX  ) ){
                            if( startTouchY < nowTouchedY + adjust ){
                                Log.v( "Flick", "--下" );
                                // 下フリック時の処理を記述する
                            }
                        }
                        else if( ( nowTouchedY - startTouchY ) < ( nowTouchedX - startTouchX ) ){
                            if( startTouchX < nowTouchedX + adjust ){
                                Log.v( "Flick", "--右" );
                                // 右フリック時の処理を記述する
                            }
                        }
                    }
                }
                break;
        }
        return (true);
    }
}
