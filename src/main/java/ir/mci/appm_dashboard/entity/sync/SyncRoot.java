//package ir.mci.appm_dashboard.entity.sync;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "sync_root")
//public class SyncRoot implements Serializable {
//    @Id
//    @Column(name = "sync_root_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Integer syncRootId;
//
//    @JsonProperty("response-code")
//    @Column(name = "response_code")
//    public String responseCode;
//
//    @OneToOne (cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "syncResponse" , referencedColumnName = "sync_response_id")
//    public SyncResponse syncResponse;
//
//}
