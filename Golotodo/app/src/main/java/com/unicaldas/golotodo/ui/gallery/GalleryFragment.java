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

    String jsonProductos = "[{\"nombre\":\"Balón\",\"categoria\":\"Futbol\",\"precio\":50000,\"enstock\":true,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_815916-MCO40929202779_022020-V.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal A\",\"area\":100,\"encargado\":{\"nombre\":\"Encargado 1\"}},{\"nombre\":\"Sucursal B\",\"area\":200,\"encargado\":{\"nombre\":\"Encargado 2\"}}]},{\"nombre\":\"Guantes\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_700137-MCO45115756330_032021-O.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]},{\"nombre\":\"Raqueta\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://s3.amazonaws.com/mercado-ideas/wp-content/uploads/sites/2/2019/10/22100017/raqueta-prince.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]},{\"nombre\":\"Pimpones\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_705376-MCO44976750617_022021-O.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]},{\"nombre\":\"Guayos\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_818984-MCO44044382745_112020-O.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]},{\"nombre\":\"Pito\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_606072-MCO31107180678_062019-V.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]},{\"nombre\":\"Canilleras\",\"categoria\":\"Carreras\",\"precio\":40000,\"enstock\":false,\"imagen\":\"https://http2.mlstatic.com/D_NQ_NP_841158-MCO44011563751_112020-V.jpg\",\"sucursales\":[{\"nombre\":\"Sucursal C\",\"area\":50,\"encargado\":{\"nombre\":\"Encargado 3\"}},{\"nombre\":\"Sucursal D\",\"area\":45,\"encargado\":{\"nombre\":\"Encargado 4\"}}]}]";
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
            //String categoria = productos.getJSONObject(position).getString("categoria");
            //String precio = productos.getJSONObject(position).getString("precio");
           // String imagen = productos.getJSONObject(position).getString("imagen");

            holder.tev_nombre_producto.setText(nombre);
           // holder.tev_categoria_producto.setText("Categoria: " + categoria);
           // holder.tev_precio_producto.setText("$" + precio);

            if (position % 2 == 0) {
                //holder.ll_item.setBackground(miActividad.getDrawable(R.drawable.item_fondo_1));
            } else {
               // holder.ll_item.setBackground(miActividad.getDrawable(R.drawable.item_fondo_2));
            }



           // holder.btn_favorito.setOnClickListener(new View.OnClickListener() {
              //  @Override
             //   public void onClick(View v) {
               //     Toast.makeText(miActividad, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();
             //   }
           // });



            //Glide.with(miActividad).load(imagen)
            //        .diskCacheStrategy(DiskCacheStrategy.ALL)
            //        .into(holder.imv_prodcuto);

           // holder.imv_prodcuto.setOnClickListener(new View.OnClickListener() {
             //   @Override
            //    public void onClick(View v) {
            //        try {
            //            Log.e("CLICK_IMGANE", productos.getJSONObject(position).toString());
             //           Intent intent = new Intent(miActividad, DetalleProductoActivity.class);
             //           intent.putExtra("producto", productos.getJSONObject(position).toString());
            //            miActividad.startActivity(intent);
             //       } catch (JSONException e) {
             //           e.printStackTrace();
             //       }
             //   }
          //  });



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
       // private TextView tev_categoria_producto;
       // private TextView tev_precio_producto;
       // private Button btn_favorito;
       // private Button btn_carrito;
       // private ImageView imv_prodcuto;
       // private LinearLayout ll_item;


        public ViewHolder(View v) {
            super(v);
            tev_nombre_producto = v.findViewById(R.id.tev_nombre_producto);
           // tev_categoria_producto = v.findViewById(R.id.tev_categoria_producto);
           // tev_precio_producto = v.findViewById(R.id.tev_precio_producto);
           // btn_favorito = v.findViewById(R.id.btn_favorito);
           // btn_carrito = v.findViewById(R.id.btn_carrito);
           // imv_prodcuto = v.findViewById(R.id.imv_producto);
           // ll_item = v.findViewById(R.id.ll_item);

        }
    }



}