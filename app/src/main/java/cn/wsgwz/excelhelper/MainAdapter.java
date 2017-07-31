package cn.wsgwz.excelhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/7/28 0028.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private Context context;
    private List<ShoesSimple> list;

    private LayoutInflater inflater;

    public MainAdapter(List<ShoesSimple> list,Context context ) {
        this.list = list;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_main_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShoesSimple shoesSimple = list.get(position);
        holder.idTV.setText(shoesSimple.getId());
        holder.creatTimeTV.setText(shoesSimple.getCreatTime());
        holder.modelNoTV.setText(shoesSimple.getModelNo());
        holder.colorTV.setText(shoesSimple.getColor());
        holder.inTheMiddleTV.setText(shoesSimple.getInTheMiddle());
        holder.buttomTV.setText(shoesSimple.getButtom());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTV,creatTimeTV,modelNoTV,colorTV,inTheMiddleTV,buttomTV;
        public ViewHolder(View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            creatTimeTV = itemView.findViewById(R.id.creatTimeTV);
            modelNoTV = itemView.findViewById(R.id.modelNoTV);
            colorTV = itemView.findViewById(R.id.colorTV);
            inTheMiddleTV = itemView.findViewById(R.id.inTheMiddleTV);
            buttomTV = itemView.findViewById(R.id.buttomTV);
        }
    }
}
