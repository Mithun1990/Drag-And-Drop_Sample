package com.example.draganddropapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewDragAndDropActivity extends Activity {
	ImageView im1, im2, im3, im4;
	int image_id[] = { R.drawable.car1, R.drawable.car2, R.drawable.car3,
			R.drawable.car4 };

	int image_1, image_2, image_3, image_4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_drag);
		im1 = (ImageView) findViewById(R.id.imageView1);
		im2 = (ImageView) findViewById(R.id.imageView2);
		im3 = (ImageView) findViewById(R.id.imageView3);
		im4 = (ImageView) findViewById(R.id.imageView4);

		im1.setImageResource(image_id[0]);
		im2.setImageResource(image_id[3]);
		im3.setImageResource(image_id[1]);
		im4.setImageResource(image_id[2]);

		image_1 = 0;
		image_2 = 1;
		image_3 = 2;
		image_4 = 3;

		im2.setOnTouchListener(new ChoiceTouchListener());

		im3.setOnDragListener(new ChoiceDragListener());

	}

	private final class ChoiceTouchListener implements OnTouchListener {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {

			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				// setup drag

				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				// start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
				return true;
			} else {
				return false;
			}
		}

	}

	private class ChoiceDragListener implements OnDragListener {

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public boolean onDrag(View v, DragEvent event) {
			View view = (View) event.getLocalState();
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				// no action necessary
				break;
			case DragEvent.ACTION_DROP:
				// handle the dragged view being dropped over a drop view
				
				view.setVisibility(View.INVISIBLE);
				// view dragged item is being dropped on
				ImageView dropTarget = (ImageView) v;

				// view being dragged and dropped
				ImageView dropped = (ImageView) view;
				Bitmap bitmap = ((BitmapDrawable) dropped.getDrawable())
						.getBitmap();
				// update the text in the target view to reflect the data being
				// dropped
				// dropTarget.setText(dropped.getText());
				// make it bold to highlight the fact that an item has been
				// dropped
				// dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

				dropTarget.setImageBitmap(bitmap);

				break;
			case DragEvent.ACTION_DRAG_ENDED:
				view.setVisibility(View.VISIBLE);
				if (image_2 - 1 >= 0) {
					image_2 = image_2 - 1;
					System.out.println(image_2);
					im2.setImageResource(image_id[image_2]);
					System.out.println("if1");

				} else {
					System.out.println("else1");
					System.out.println(image_2);
					image_2 = image_id.length - 1;
					im2.setImageResource(image_id[image_2]);
				}

				if (image_4 - 1 >= 0) {

					image_4 = image_4 - 1;
					System.out.println(image_4);
					im4.setImageResource(image_id[image_4]);
					System.out.println("if2");

				} else {
					System.out.println("else1");

					image_4 = image_id.length - 1;
					System.out.println(image_4);
					im4.setImageResource(image_id[image_4]);
				}

				if (image_1 - 1 >= 0) {
					System.out.println("if3");

					image_1 = image_1 - 1;
					System.out.println(image_1);
					im1.setImageResource(image_id[image_1]);

				} else {
					System.out.println("else1");

					image_1 = image_id.length - 1;
					System.out.println(image_1);
					im1.setImageResource(image_id[image_1]);
				}

				if (image_3 - 1 >= 0) {
					image_3 = image_3 - 1;
				} else {
					image_3 = image_id.length - 1;
				}

				break;
			default:
				break;
			}
			return true;
		}
	}

}
