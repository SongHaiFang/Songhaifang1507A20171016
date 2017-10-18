package demo.song.com.songhaifang1507a20171016.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import demo.song.com.songhaifang1507a20171016.R;
import demo.song.com.songhaifang1507a20171016.bean.MyBean;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private MyBean bean;
    private Context context;

    public HomeAdapter(MyBean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener onItemClickListener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.er, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(holder.tv, "alpha", 0.3f, 1f);
        alpha.setDuration(2000).start();

        holder.tv.setText(bean.song_list.get(position).title);
        loderImage(bean.song_list.get(position).pic_big, holder.img);
        holder.auther.setText(bean.song_list.get(position).author);if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return bean.song_list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv,auther;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            img =(ImageView) view.findViewById(R.id.img_er);
            auther= view.findViewById(R.id.id_auther);
        }
    }
    public  void loderImage(String url,ImageView imageView){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);
        //            String path = Environment.getExternalStorageDirectory().getPath()+"/"+"Pictrues";
        //            File file = new File(path);
        //
        //            ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getActivity())
        //                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
        //                .memoryCacheSize(2 * 1024 * 1024)//配置内存缓存的大小  例如 : 2* 1024 * 1024 = 2MB
        //                .build();//配置构建完成   修改缓存位置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader instance = ImageLoader.getInstance();
        instance.init(configuration);
        instance.displayImage(url, imageView ,options);

    }

}