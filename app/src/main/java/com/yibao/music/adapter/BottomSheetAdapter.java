package com.yibao.music.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibao.music.MusicApplication;
import com.yibao.music.R;
import com.yibao.music.base.BaseRvAdapter;
import com.yibao.music.model.BottomSheetStatus;
import com.yibao.music.model.MusicBean;
import com.yibao.music.util.RxBus;
import com.yibao.music.util.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Des：${快速列表的Adapter}
 * Time:2017/8/22 14:31
 *
 * @author Stran
 */
public class BottomSheetAdapter
        extends BaseRvAdapter<MusicBean> {


    public BottomSheetAdapter(List<MusicBean> list) {
        super(list);

    }

    @Override
    protected String getLastItemDes() {
        return " 首歌";
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder holder, MusicBean musicItem) {
        if (holder instanceof MusicHolder) {
            MusicHolder musicHolder = (MusicHolder) holder;
            musicHolder.mMusicName.setText(musicItem.getTitle());
            String unknownName = "<unknown>";
            musicHolder.mMusicSinger.setText(unknownName.equals(musicItem.getArtist()) ? "Smartisan" : musicItem.getArtist());
            musicHolder.mFavoriteTime.setText(StringUtil.getFormatDate(Long.valueOf(musicItem.getTime())));
            int position = musicHolder.getAdapterPosition();
//            MusicBottomSheetDialog页面接收,用于播放收藏列表中点击Position的音乐
            musicHolder.mRootBottomSheet.setOnClickListener(view -> RxBus.getInstance().post(new BottomSheetStatus(
                    position)));
        }
    }


    @Override
    protected RecyclerView.ViewHolder getViewHolder(View view) {
        return new MusicHolder(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bottom_sheet_music_item;
    }


    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int i) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    static class MusicHolder
            extends RecyclerView.ViewHolder {
        @BindView(R.id.favorite_music_name)
        TextView mMusicName;
        @BindView(R.id.favorite_artist_name)
        TextView mMusicSinger;
        @BindView(R.id.favorite_time)
        TextView mFavoriteTime;
        @BindView(R.id.bottom_list_itme_clear)
        ImageView mBottomListItmeClear;
        @BindView(R.id.root_bottom_sheet)
        LinearLayout mRootBottomSheet;

        MusicHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
