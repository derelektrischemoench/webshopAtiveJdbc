package model;


import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Many2Many;
import org.javalite.activejdbc.annotations.Table;

//the join in m2m is the name of the join table


@Table("shoppingcarts")
@Many2Many(other = Record.class, join = "record_shoppingcart", sourceFKName = "id", targetFKName = "id")
public class Shoppingcart extends Model {}
