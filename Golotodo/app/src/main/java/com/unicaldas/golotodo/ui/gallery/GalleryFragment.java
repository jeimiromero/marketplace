package com.unicaldas.golotodo.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unicaldas.golotodo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GalleryFragment<db> extends Fragment {

    private Spinner spn_categorias;
    private TextView tev_gallery;

    private RecyclerView rev_productos;
    private RecyclerView.Adapter mAdapter;

    String jsonProductos = "[{\"nombre\":\"Bandeja Paisa\",\"categoria\":\"plato fuerte\",\"precio\":35000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2017/10/platos-tipicos-de-Colombia-bandeja-paisa-min-630x420.jpg\"},{\"nombre\":\"Sancocho Antioqueño\",\"categoria\":\"plato fuerte\",\"precio\":25000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2017/10/sancocho-colombiano-foto-min-630x420.jpg\"},{\"nombre\":\"Cocido Boyacense\",\"categoria\":\"plato fuerte\",\"precio\":30000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2020/02/686px-Cocido_boyaco-1-561x420.jpg\"},{\"nombre\":\"Fritanga Bogotana\",\"categoria\":\"plato fuerte\",\"precio\":20000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2017/10/Fritanga-bogotana-colombiana-min-696x392.jpg\"},{\"nombre\":\"Viudo de Bocachico o Capaz\",\"categoria\":\"plato fuerte\",\"precio\":45000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/bocachico-1.jpg\"},{\"nombre\":\"Carne Oreada\",\"categoria\":\"plato fuerte\",\"precio\":30000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/crte-oreada-1-630x420.jpg\"},{\"nombre\":\"Chanfaina\",\"categoria\":\"plato fuerte\",\"precio\":20000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/cafaina-1-629x420.jpg\"},{\"nombre\":\"Carne puyada\",\"categoria\":\"plato fuerte\",\"precio\":30000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/carne-puyada-1-560x420.jpg\"},{\"nombre\":\"Arroz Apastelado\",\"categoria\":\"plato fuerte\",\"precio\":30000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/arroz-empastelado-1-629x420.jpg\"},{\"nombre\":\"Butifarra Soledeña\",\"categoria\":\"plato fuerte\",\"precio\":25000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/butifarras-saldena-1.jpg\"},{\"nombre\":\"Cuy\",\"categoria\":\"plato fuerte\",\"precio\":20000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/Conejillo-de-Indias-1-630x420.jpg\"},{\"nombre\":\"Locro\",\"categoria\":\"plato fuerte\",\"precio\":20000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/Locro-1-630x420.jpg\"},{\"nombre\":\"Arroz Atollado\",\"categoria\":\"plato fuerte\",\"precio\":30000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/Arroz-atollado-1-630x420.jpg\"},{\"nombre\":\"Ternera a La Llanera o Mamona\",\"categoria\":\"plato fuerte\",\"precio\":25000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/carne-tarnera-1-629x420.jpg\"},{\"nombre\":\"Tungos de Arroz\",\"categoria\":\"plato fuerte\",\"precio\":15000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/Tungos-de-arroz-1-631x420.jpg\"},{\"nombre\":\"Cachama en Salsa o “Sudada”\",\"categoria\":\"plato fuerte\",\"precio\":25000,\"enstock\":true,\"imagen\":\"https://www.lifeder.com/wp-content/uploads/2021/01/Cachama-en-salsa-o-sudada-1-613x420.jpg\"}]";
    String jsonProducto = "{\"nombre\":\"Balón\",\"precio\":50000,\"enstock\":true}";

    String[] categorias = new String[]{"Futbol", "Baloncesto", "Golf", "Natación", "Carreras"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        tev_gallery = root.findViewById(R.id.tev_gallery);
        tev_gallery.setText("golotodo");

        spn_categorias = root.findViewById(R.id.spn_categoria);

         rev_productos = root.findViewById(R.id.rev_productos);

        rev_productos.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rev_productos.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        try {
            JSONArray productos = new JSONArray(jsonProductos);
            mAdapter = new ProductosAdapter(productos,getActivity());

            rev_productos.setAdapter(mAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        try {

            JSONArray productos = new JSONArray(jsonProductos);



            JSONObject producto = new JSONObject(jsonProducto);

            JSONObject producto0 = productos.getJSONObject(0);



            int precio = producto0.getInt("precio");

            JSONArray sucursales = producto0.getJSONArray("sucursales");

            JSONObject sucursalNueva = new JSONObject();
            sucursalNueva.put("nombre", "Sucursal nueva");
            sucursalNueva.put("area", 34);

            sucursales.put(sucursalNueva);



            //Log.e("JSON_NUEVO", productos.toString());

            String nombreSucursal = sucursales.getJSONObject(1).getString("nombre");

            //Toast.makeText(getActivity(), "Precio: " + precio, Toast.LENGTH_SHORT).show();

            //Toast.makeText(getActivity(), "Precio: " + productos.getJSONObject(1).getString("precio"), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(), "Nombre: " + producto.getString("nombre"), Toast.LENGTH_SHORT).show();

            Toast.makeText(getActivity(), "Encargado: " + productos.getJSONObject(1).getJSONArray("sucursales").getJSONObject(1).getJSONObject("encargado").getString("nombre"), Toast.LENGTH_SHORT).show();

            //ProductosAdapter mAdapater = new ProductosAdapter(productos, getActivity());

            //rev_productos.setAdapter(mAdapater);

        } catch (JSONException e) {
            e.printStackTrace();
        }




        // rev_productos.setLayoutManager(new GridLayoutManager(getActivity(), 3));


        //Opcion 1: String array de la carpeta values
        //ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity(), R.array.categorias,
        //        android.R.layout.simple_spinner_dropdown_item);

        //Opcion 2: usando un array java
        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
        //        categorias);

        spn_categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String categoriaSeleccionada = categorias[position];
                String categoriaSeleccionada = spn_categorias.getSelectedItem().toString();
                tev_gallery.setText(categoriaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spn_categorias.setAdapter(adaptador);



        return root;
    }
}

    class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {

    private JSONArray productos;

    private Activity miActividad;

    public ProductosAdapter(JSONArray productos, Activity miActividad) {
        this.productos = productos;
        this.miActividad = miActividad;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_productos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        try {

            Log.e("POS_rec", "POS: " + position);
            String nombre = productos.getJSONObject(position).getString("nombre");
            String categoria = productos.getJSONObject(position).getString("categoria");
            String precio = productos.getJSONObject(position).getString("precio");
            String imagen = productos.getJSONObject(position).getString("imagen");

            holder.tev_nombre_producto.setText(nombre);
            holder.tev_categoria_producto.setText("Categoria: " + categoria);
            holder.tev_precio_producto.setText("$" + precio);

            if (position % 2 == 0) {
                //holder.ll_item.setBackground(miActividad.getDrawable(R.drawable.item_fondo_1));
            } else {
                //holder.ll_item.setBackground(miActividad.getDrawable(R.drawable.item_fondo_2));
            }



            holder.btn_favorito.setOnClickListener(new View.OnClickListener() {
              @Override
                public void onClick(View v) {
                    Toast.makeText(miActividad, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();
                }
            });



            Glide.with(miActividad).load(imagen)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imv_prodcuto);

            holder.imv_prodcuto.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
                   // try {
                   //     Log.e("CLICK_IMGANE", productos.getJSONObject(position).toString());
                  //      Intent intent = new Intent(miActividad, DetalleProductoActivity.class);
                    //    intent.putExtra("producto", productos.getJSONObject(position).toString());
                  //      miActividad.startActivity(intent);
                  //  } catch (JSONException e) {
                  //      e.printStackTrace();
                   // }
                }
            });



        } catch (JSONException e) {
            holder.tev_nombre_producto.setText("error");
        }

    }

    @Override
    public int getItemCount() {
//        return userModelList.size();
        return this.productos.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tev_nombre_producto;
        private TextView tev_categoria_producto;
        private TextView tev_precio_producto;
        private Button btn_favorito;
        private Button btn_carrito;
        private ImageView imv_prodcuto;
        private LinearLayout ll_item;


        public ViewHolder(View v) {
            super(v);
            tev_nombre_producto = v.findViewById(R.id.tev_nombre_producto);
            tev_categoria_producto = v.findViewById(R.id.tev_categoria_producto);
            tev_precio_producto = v.findViewById(R.id.tev_precio_producto);
            btn_favorito = v.findViewById(R.id.btn_favorito);
            btn_carrito = v.findViewById(R.id.btn_carrito);
            imv_prodcuto = v.findViewById(R.id.imv_producto);
            ll_item = v.findViewById(R.id.ll_item);

        }
    }



}