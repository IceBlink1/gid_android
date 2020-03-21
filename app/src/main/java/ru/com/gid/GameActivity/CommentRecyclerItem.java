package ru.com.gid.GameActivity;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

import ru.com.gid.R;

public class CommentRecyclerItem extends Item {

    private String comment;

    public CommentRecyclerItem(String comment) {
        this.comment = comment;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        ((TextView)viewHolder.itemView.findViewById(R.id.game_comment_content)).setText(comment);
    }

    @Override
    public int getLayout() {
        return R.layout.game_comment_recycler_item;
    }
}
