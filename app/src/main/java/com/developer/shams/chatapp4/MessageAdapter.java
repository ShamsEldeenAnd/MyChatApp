package com.developer.shams.chatapp4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    private Context context;

    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        LinearLayout layout = convertView.findViewById(R.id.mylayout);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            if (!message.getName().equals(MainActivity.mUsername)) {
                layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                messageTextView.setTextColor(Color.parseColor("#2b2b2b"));
                messageTextView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_corner_com));
            } else {
                layout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                messageTextView.setTextColor(Color.parseColor("#ffffff"));
                messageTextView.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_corner));
            }

            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }

        authorTextView.setText(message.getName());

        return convertView;
    }
}
