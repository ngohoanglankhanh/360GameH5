package com.ngo.lap11877_khanh.a360_h5_2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    List<Game> mGameList = new ArrayList<>();

    Context mContext;

    private OnClickDetailListener mOnClickDetailListener;


    public GameAdapter(Context context) {
        mContext = context;
    }

    public void setOnClickDetailListener(OnClickDetailListener onClickDetailListener) {
        mOnClickDetailListener = onClickDetailListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.bindView(mGameList.get(position));
    }

    public void setGameList(List<Game> gameList) {
        mGameList.clear();
        if (gameList != null && !gameList.isEmpty()) {
            mGameList.addAll(gameList);
        }
        notifyDataSetChanged();
    }

    public void appendGameList(List<Game> gameList) {
        if (gameList != null && !gameList.isEmpty()) {
            mGameList.addAll(gameList);
        }
        notifyDataSetChanged();
    }

    private Game getItem(int pos) {
        if (isValidPos(pos)) {
            return mGameList.get(pos);
        }
        return null;
    }

    private boolean isValidPos(int pos) {
        return 0 <= pos && pos < mGameList.size();
    }


    @Override
    public int getItemCount() {
        return mGameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle, mTopic, mPlayTimes, mAppDetails, mIconStatus;

        ImageView mImageIcon;

        View mGameItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.game_name);
            mTopic = itemView.findViewById(R.id.game_topic);
            mPlayTimes = itemView.findViewById(R.id.game_playTimes);
            mAppDetails = itemView.findViewById(R.id.game_details);
            mIconStatus = itemView.findViewById(R.id.game_status);
            mImageIcon = itemView.findViewById(R.id.game_image_icon);
            mGameItem = itemView.findViewById(R.id.list_game_layout);


            mGameItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickDetailListener != null) {
                        mOnClickDetailListener.onClickDetail(getItem(getAdapterPosition()));
                    }
                }
            });
        }

        public void bindView(Game mGame) {
            if (mGame != null) {
                mTitle.setText(mGame.getTitle());

                setTopic(mGame.getTopic());

                setStatus(mGame.getIconStatus());

                setPlayTimes(mGame.getPlayTimes());

                Glide.with(mContext)
                        .asBitmap()
                        .load(mGame.getImageIcon())
                        .into(mImageIcon);
            }
        }

        private void setStatus(String iconStatus) {
            if (TextUtils.isEmpty(iconStatus)) {
                mIconStatus.setVisibility(View.GONE);
            } else {
                mIconStatus.setVisibility(View.VISIBLE);
                mIconStatus.setText(iconStatus);
            }
        }

        public void setTopic(String topicName) {
            final Context context = itemView.getContext();
            final String topicTitle = context.getString(R.string.topic_title);
            final String topicFormat = context.getString(R.string.full_topic_format);
            final String topic = String.format(topicFormat, topicTitle, topicName);

            final int topicTitleStartIndex = topic.indexOf(topicTitle);
            final int topicTitleEndIndex = topicTitleStartIndex + topicTitle.length();
            final SpannableString spannableString = new SpannableString(topic);
            final ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);

            spannableString.setSpan(foregroundColorSpan, topicTitleStartIndex, topicTitleEndIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mTopic.setText(spannableString);
        }

        public void setPlayTimes(String playTimesValue) {
            final String players = mContext.getString(R.string.players);
            final String playTimesFormat = mContext.getString(R.string.full_play_times_format);
            final String playTimes = String.format(playTimesFormat, playTimesValue, players);

            final int playersStartIndex = playTimes.indexOf(players);
            final int playersEndIndex = playersStartIndex + players.length();

            final SpannableString spannableString = new SpannableString(playTimes);
            final ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.GRAY);

            spannableString.setSpan(foregroundColorSpan, playersStartIndex, playersEndIndex,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            mPlayTimes.setText(spannableString);
        }
    }

    /**
     * {@link OnClickDetailListener}
     */
    public interface OnClickDetailListener {
        void onClickDetail(Game game);
    }
}