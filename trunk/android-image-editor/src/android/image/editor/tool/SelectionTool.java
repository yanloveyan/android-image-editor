package android.image.editor.tool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.image.editor.ImageEditorView;

public class SelectionTool implements Tool {
	
	private float startX, startY;
	private RectF selection;
	private Paint       mPaint;

	public SelectionTool() {
		mPaint = new Paint();
		mPaint.setStyle(Style.STROKE);
	}
	
	@Override
	public void touchStart(ImageEditorView context, float x, float y) {
		startX = x;
		startY = y;
		clearSelection();
		context.invalidate();
	}

	@Override
	public void touchMove(ImageEditorView context, float x, float y) {
		float left = Math.min(startX, x);
		float top = Math.min(startY, y);
		float right = Math.max(startX, x);
		float bottom = Math.max(startY, y);
		selection = new RectF(left, top, right, bottom);
		context.invalidate();
	}

	@Override
	public void touchUp(ImageEditorView context) {
		
	}

	@Override
	public void onDraw(ImageEditorView context, Canvas canvas) {
		canvas.drawColor(0xFFAAAAAA);

        context.drawBitmap(canvas);
        
        if (selection != null) {
        	canvas.drawRect(selection, mPaint);
        }
	}

	public RectF getSelection() {
		return selection;
	}
	
	public void clearSelection() {
		selection = null;
	}
	
	public boolean selectionExists() {
		return selection != null;
	}
	
}