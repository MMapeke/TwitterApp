package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    //Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }

    // given position Bind the values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data
        Tweet tweet = tweets.get(position);
        //bind the tweet with the view holder
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView relativeTime;
        ImageView tweetImage;
        TextView name;

        //representing one row/ a tweet
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            relativeTime = itemView.findViewById(R.id.relativeTime);
            tweetImage = itemView.findViewById(R.id.tweetPic);
            name = itemView.findViewById(R.id.name);
        }

        public void bind(Tweet tweet) {
            //use tweet attributes to fill views
            tvBody.setText(tweet.body);
            name.setText(" @" + tweet.user.screenName) ;
            tvScreenName.setText(tweet.user.name);
            relativeTime.setText(tweet.relativeTime);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            if(tweet.imageUrl != null){
                //if image there se the tweetImage
                Glide.with(context)
                        .load(tweet.imageUrl)
//                        .transform(new RoundedCorners(25))
                        .into(tweetImage);
            }else{
                //otherwise set to gone
                tweetImage.setVisibility(View.GONE);
            }
        }
    }

    // clean all items in recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // add a list of items
    public void addAll(List<Tweet> list){
        tweets.addAll(list);
        notifyDataSetChanged();
    }
}
