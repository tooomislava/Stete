package tzilic_20.stete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tzilic_20.stete.model.Steta;


public class AdapterListe extends RecyclerView.Adapter<AdapterListe.Red> {

    private List<Steta> podaci;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public AdapterListe(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        View view = mInflater.inflate(R.layout.red_liste, roditelj, false);
        return new Red(view);
    }

    @Override
    public void onBindViewHolder(Red red, int position) {
        Steta o = podaci.get(position);
        red.vlasnik.setText(o.getVlasnik());
        red.geografskaSirina.setText(String.valueOf(o.getGeografskaSirina()));
        red.geografskaDuzina.setText(String.valueOf(o.getGeografskaDuzina()));
        red.iznosStete.setText(String.valueOf(o.getIznosStete()));

    }

    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView vlasnik;
        private TextView geografskaSirina;
        private TextView geografskaDuzina;
        private TextView iznosStete;

        Red(View itemView) {
            super(itemView);
            vlasnik = itemView.findViewById(R.id.vlasnik);
            geografskaSirina = itemView.findViewById(R.id.geografskaSirina);
            geografskaDuzina = itemView.findViewById(R.id.geografskaDuzina);
            iznosStete = itemView.findViewById(R.id.iznosStete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Steta getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Steta> itemList) {
        this.podaci = itemList;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
