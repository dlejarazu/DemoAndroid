package com.diego.civpocket;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.diego.civpocket.logic.MapPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 15/10/2014.
 */
public class MapDrawView extends ImageView implements View.OnTouchListener {

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP &&
                regiones != null &&
                regiones.size() > 0)
        {
            int x = (int)motionEvent.getX();
            int y = (int)motionEvent.getY();
            double minDistance = 150;
            for(RegionWidget reg: regiones){
                double newDistance = Math.sqrt(Math.pow(reg.getX() - x,2) +
                                     Math.pow(reg.getY() - y,2));
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    mapPModel.accionSelectRegion(reg.getId());
                }
            }
        }
        return true;
    }

    class RegionWidget {
        String id;
        String _textToShow;
        int _coordX, _coordY;
        Paint _TextPaint;

        public String getId() {return id;}
        public RegionWidget(String newId)
        {
            id = newId;
            _TextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            _TextPaint.setColor(Color.BLUE);
            // Convert the dips to pixels
            float scale = getContext().getResources().getDisplayMetrics().density;
            float sizeText = (int) (16.0f * scale + 0.5f);
            _TextPaint.setTextSize(sizeText);
        }

        public void selected(boolean isSelected)
        {
           if (isSelected) _TextPaint.setColor(Color.RED);
           else _TextPaint.setColor(Color.BLUE);
        }

        void set_textToShow(String nuevoTexto) { _textToShow = nuevoTexto; }
        void setPos(int nuevaX, int nuevaY) {
            _coordX = nuevaX;
            _coordY = nuevaY;
        }
        int getX() {return  coordImgToView(_coordX,_coordY)[0];}
        int getY() {return  coordImgToView(_coordX,_coordY)[1];}
        public void draw(Canvas c){
            c.drawText(_textToShow,getX(),getY(),_TextPaint);
        }
    }

    List<RegionWidget> regiones = new ArrayList<RegionWidget>();
    MapPresenter mapPModel = null;

    public MapDrawView(Context context)
    {
        super(context);
        init();
    }

    public MapDrawView(Context context, AttributeSet attr)
    {
        super(context, attr);
        init();
    }

    public MapDrawView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public void setPresenter(MapPresenter newPresenter) {
        mapPModel = newPresenter;
    }

    void init() {
        this.setOnTouchListener(this);
    }

    public int[] coordImgToView(int coordImgX, int coordImgY)
    {
        Drawable drawable = this.getDrawable();
        Rect imageBounds = drawable.getBounds();

        int realBMPHeight = drawable.getIntrinsicHeight();
        int realBMPWidth = drawable.getIntrinsicWidth();

        int scaledHeight = this.getHeight();
        int scaledWidth = this.getWidth();

        float heightRatio = (float)realBMPHeight / (float)scaledHeight;
        float widthRatio = (float)realBMPWidth / (float)scaledWidth;

        double scaledImageOffsetX =  coordImgX / widthRatio;
        double scaledImageOffsetY =  coordImgY / heightRatio;
        int coordXview =(int)(scaledImageOffsetX + imageBounds.left);
        int coordYview = (int)(scaledImageOffsetY + imageBounds.top);

        return new int[]{coordXview, coordYview};
    }

    public void AddRegion(String id, int posX, int posY){
        RegionWidget newRegion = new RegionWidget(id);
        newRegion.setPos(posX, posY);
        regiones.add(newRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mapPModel != null) {
            for (RegionWidget reg : regiones) {
                reg.set_textToShow(mapPModel.regionStatusToString(reg.getId()));
                reg.selected(mapPModel.isSelected(reg.getId()));
                reg.draw(canvas);
            }
        }
    }

}
