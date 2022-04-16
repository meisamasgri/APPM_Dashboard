//package ir.mci.appm_dashboard.entity.sync;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Entity
//@Table(name= "sync_response")
//public class SyncResponse implements Serializable {
//
//    @Id
//    @Column(name = "sync_response_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Integer syncResponseId;
//
//    @OneToOne(mappedBy = "syncResponse")
//    public SyncRoot syncRoot;
//
//    @OneToMany (mappedBy = "syncResponse" , cascade=CascadeType.PERSIST, fetch = FetchType.LAZY)
//    public List<SyncResult> syncResult;
//
//    @Column(name = "uri")
//    public String uri;
//}
//
